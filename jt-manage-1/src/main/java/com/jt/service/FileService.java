package com.jt.service;

import org.springframework.web.multipart.MultipartFile;

import com.jt.vo.FileVo;

public interface FileService {

	FileVo upload(MultipartFile uploadFile);

}
