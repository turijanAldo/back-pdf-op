package com.thincode.savepdf.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author carpinteyror
 * @version 1.0
 * @date 15 oct. 2020
 */
@Setter
@Getter
@Entity
@Table(name = "evidence")
// @Builder
public class Evidence implements Serializable {

  private static final long serialVersionUID = -6291727080273876293L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "IDEVIDENCE")
  private Long idEvidence;

  @Column(name = "NAME")
  private String name;

  @Column(name = "FILE")
  private String file;

  @Column(name = "TYPE")
  private String type;

  @Column(name = "CREATIONDATE")
  private LocalDateTime creationDate;

}
