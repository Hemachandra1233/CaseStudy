package com.financemanagement.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.financemanagement.demo.entity.Bills;
import com.financemanagement.demo.repository.BillsRepository;
import com.financemanagement.demo.service.FilesService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@Controller
public class FileResourceController {

	@Autowired
	private FilesService filesService;

	// Location To store uploaded files
	public static String DIRECTORY = System.getProperty("user.home") + "/Downloads/";

	// Method to download files
	@GetMapping("download/{filename}/{transactionId}")
	public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename,
			@PathVariable(name = "transactionId") Long transactionId) throws IOException {

		return filesService.downloadFiles(filename, transactionId);
	}

	@GetMapping("filenames/{transactionId}")
	public ResponseEntity<List<Bills>> getAllFileNames(@PathVariable(name = "transactionId") Long transactionId) {
		return filesService.getAllFileNames(transactionId);
	}

	@PostMapping("/upload/{transactionId}")
//	@Transactional
	public ResponseEntity<List<String>> uploadFiles2(@RequestParam("files") List<MultipartFile> multipartFiles,
			@PathVariable(name = "transactionId") Long transactionId) throws IOException {
		return filesService.uploadFiles2(multipartFiles, transactionId);
	}

}
