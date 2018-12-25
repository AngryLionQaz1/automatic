package com.snow.automatic.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "s_web",uniqueConstraints = {@UniqueConstraint(columnNames = "projectName")},indexes = {@Index(columnList = "projectName")})
@AllArgsConstructor
@NoArgsConstructor
public class Web {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**项目名*/
    private String projectName;
    /**项目创库地址*/
    private String gitPath;
    /**项目描述*/
    @Lob
    private String projectContent;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;









}
