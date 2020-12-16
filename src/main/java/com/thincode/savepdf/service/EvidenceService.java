package com.thincode.savepdf.service;

import com.thincode.savepdf.bean.vo.EvidenceRequest;
import com.thincode.savepdf.bean.vo.Response;
import com.thincode.savepdf.model.Evidence;
import com.thincode.savepdf.model.EvidenceRepository;
import com.thincode.savepdf.util.Util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

/**
 * @author carpinteyror
 * @version 1.0
 * @date 15 oct. 2020
 */
@Service
@RequiredArgsConstructor
public class EvidenceService {

  private final EvidenceRepository evidenceRepository;
  private final Util util;

  public Response<Void> savePDFFile(EvidenceRequest request) {

    Evidence entity = util.copyFields(request, Evidence.class);
    entity.setType("PDF");
    entity.setCreationDate(LocalDateTime.now());
    evidenceRepository.save(entity);
    return util.setDataResponse(null);
  }

  public Response<List<Evidence>> allFiles() {
    List<Evidence> list = (List<Evidence>) evidenceRepository.findAll();
    return util.setDataResponse(list);
  }

  /**
   * @param id
   * @return
   */
  public Response<Evidence> findFileById(Long id) {
    Evidence data = null;
    Optional<Evidence> evi = evidenceRepository.findById(id);
    if (evi.isPresent()) {
      data = evi.get();
    }
    return util.setDataResponse(data);
  }

}
