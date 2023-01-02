package com.financemanagement.demo.service;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.financemanagement.demo.entity.Bills;
import com.financemanagement.demo.repository.BillsRepository;

@Service
public class FilesService {

	@Autowired
	private BillsRepository billsRepo;

	// Location To store uploaded files
	public static String DIRECTORY = System.getProperty("user.home") + "/Downloads/";

	public ResponseEntity<List<String>> uploadFiles2(List<MultipartFile> multipartFiles, Long transactionId)
			throws IOException {

		List<String> filenames = new ArrayList<>();
		for (MultipartFile file : multipartFiles) {
			String filename = StringUtils.cleanPath(file.getOriginalFilename());

			String DIRECTORY1 = DIRECTORY + transactionId;
			File f1 = new File(DIRECTORY1);
			String DIRECTORY2 = f1.getPath();
			f1.mkdir();

			Bills bills = new Bills();
			bills.setOriginalFileName(filename);
			bills.setTransactionId(transactionId);
//			bills.setFileName(null);
			billsRepo.save(bills);

			List<Bills> bills2 = billsRepo.findByTransactionId(transactionId);
			for (int i = 0; i < bills2.size(); i++) {
				Long id = bills2.get(i).getId();
				String extension = "";
				String fileName;

				int index = bills2.get(i).getOriginalFileName().lastIndexOf('.');
				if (index > 0) {
					extension = bills2.get(i).getOriginalFileName().substring(index + 1);
					fileName = id + "." + extension;
					bills2.get(i).setFileName(fileName);
					billsRepo.save(bills2.get(i));
					Path fileStorage = get(DIRECTORY2, fileName).toAbsolutePath().normalize();

					copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
					filenames.add(fileName);
				}
			}

		}
		return ResponseEntity.ok().body(filenames);
	}

	public ResponseEntity<List<Bills>> getAllFileNames(Long transactionId) {
		List<Bills> bills = new ArrayList<>();
		bills = billsRepo.findByTransactionId(transactionId);

		return ResponseEntity.ok().body(bills);
	}

	public ResponseEntity<Resource> downloadFiles(String filename, Long transactionId) throws IOException {

		String DIRECTORY1 = DIRECTORY + transactionId;
		Path filePath = get(DIRECTORY1).toAbsolutePath().normalize().resolve(filename);
		if (!Files.exists(filePath)) {
			throw new FileNotFoundException(filename + " was not found on the server");
		}
		Resource resource = new UrlResource(filePath.toUri());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("File-Name", resource.getFilename());
		httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
				.headers(httpHeaders).body(resource);
	}
}
