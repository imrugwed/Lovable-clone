package com.rocks.lovable_clone.mapper;

import com.rocks.lovable_clone.dto.project.FileNode;
import com.rocks.lovable_clone.entity.ProjectFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}
