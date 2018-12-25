package com.snow.automatic.common.repository;

import com.snow.automatic.common.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<Project> findByProjectName(String projectName);
}
