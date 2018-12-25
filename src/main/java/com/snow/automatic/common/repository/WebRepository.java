package com.snow.automatic.common.repository;

import com.snow.automatic.common.pojo.Web;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebRepository extends JpaRepository<Web,Long> {

    Optional<Web> findByProjectName(String projectName);


}
