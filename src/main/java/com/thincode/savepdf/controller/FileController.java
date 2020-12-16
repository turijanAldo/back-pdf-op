package com.thincode.savepdf.controller;


import com.thincode.savepdf.bean.vo.EvidenceRequest;
import com.thincode.savepdf.bean.vo.Response;
import com.thincode.savepdf.model.Evidence;
import com.thincode.savepdf.service.EvidenceService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author carpinteyror
 * @version 1.0
 * @date 15 oct. 2020
 */
@CrossOrigin
@RestController
@RequestMapping("/v1/files")
@RequiredArgsConstructor
public class FileController {

  private final EvidenceService service;

  @GetMapping("")
  public ResponseEntity<Response<List<Evidence>>> getAllFiles() {
    return new ResponseEntity<>(service.allFiles(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<Evidence>> getFile(@PathVariable("id") Long id) {
    return new ResponseEntity<>(service.findFileById(id), HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<Response<Void>> saveFile(@RequestBody EvidenceRequest request) {
    return new ResponseEntity<>(service.savePDFFile(request), HttpStatus.OK);
  }



}
