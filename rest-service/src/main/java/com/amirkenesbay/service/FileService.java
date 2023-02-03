package com.amirkenesbay.service;

import com.amirkenesbay.entity.AppDocument;
import com.amirkenesbay.entity.AppPhoto;
import com.amirkenesbay.entity.BinaryContent;
import org.springframework.core.io.FileSystemResource;

public interface FileService {
    AppDocument getDocument(String id);
    AppPhoto getPhoto(String id);
    FileSystemResource getFileSystemResource(BinaryContent binaryContent);

}
