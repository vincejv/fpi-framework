<img src="banner.png" alt="FPI Framework" width="500"/>

[![Maven Central version](https://img.shields.io/maven-central/v/com.abavilla/fpi-framework?logo=apache-maven)](https://search.maven.org/artifact/com.abavilla/fpi-framework-core)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/vincejv/fpi-framework/Maven%20Central%20deployment?label=CI/CD&logo=github)](https://github.com/vincejv/fpi-framework/actions?query=workflow%3A%22Maven+Central+deployment%22)
[![License](https://img.shields.io/github/license/vincejv/fpi-framework?logo=apache)](https://github.com/vincejv/fpi-framework/blob/main/LICENSE)
[![GitHub commit activity](https://img.shields.io/github/commit-activity/m/vincejv/fpi-framework?label=commits&logo=git)](https://github.com/vincejv/fpi-framework/pulse)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=alert_status)](https://sonarcloud.io/dashboard?id=vincejv_fpi-framework)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=vincejv_fpi-framework)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=vincejv_fpi-framework)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=vincejv_fpi-framework)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=vincejv_fpi-framework)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=security_rating)](https://sonarcloud.io/dashboard?id=vincejv_fpi-framework)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=vincejv_fpi-framework&metric=ncloc)](https://sonarcloud.io/dashboard?id=vincejv_fpi-framework)


### Sample Usage
#### Entity
```java
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@BsonDiscriminator
@MongoEntity(collection="students")
public class Student extends AbsMongoItem {
  /** Student name */
  private String name;
  /** Student home address */
  private String address;
  /** Student gender */
  private String gender;

  // Getters and setters are generated by lombok
  // during compile time, no need to specify them
}
```

#### DTO (Data Transfer Object)
```java
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class StudentDto extends AbsDto {
  /** Student name */
  private String name;
  /** Student gender */
  private String gender;
  
  // Getters and setters are generated by lombok
  // during compile time, no need to specify them
}
```

#### Repository
```java
@ApplicationScoped
public class StudentRepo 
  extends AbsMongoRepo<Student> {
  // No need to specify a body as AbsMongoRepo
  // extends from Panache repository, and already
  // implements the basic CRUD methods
}
```

#### DTO to Entity Mapstruct Mapper
```java
@Mapper(componentModel = MappingConstants.ComponentModel.CDI,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface StudentMapper 
  extends IDtoToEntityMapper<StudentDto, Student> {
  // Mapstruct will generate the mappings during compile time,
  // and mo need to specify a body as IDtoToEntityMapper specifies
  // the commonly used mapping methods
}
```

#### Service Layer
```java
@ApplicationScoped
public class StudentSvc
  // Alternatively, may extend with AbsRepoSvc if
  // you need a customized repository
  extends AbsSvc<StudentDto, Student> {
  /** Mapstruct Mapper */
  @Inject
  StudentMapper mapper;
  
  // Built in asynchronous (Mutiny) CRUD methods, all you 
  // have to specify are the mapping methods to convert
  // a DTO to entity and vice versa
  
  @Override
  public StudentDto mapToDto(Student entity) {
    return mapper.mapToDto(entity);
  }

  @Override
  public Student mapToEntity(StudentDto dto) {
    return mapper.mapToEntity(dto);
  }
}
```

#### REST Controller
```java
@Path("/fpi/cx")
public class StudentResource
    // Alternatively, may extend from AbsBaseResource,
    // or AbsReadOnlyResource for specific use case scenarios 
    extends AbsResource<StudentDto, Student, StudentSvc> {
  // ...
  // Built in asynchronous (Mutiny) CRUD methods for GET, POST,
  // PATCH, DELETE are implemented by default, you may customize
  // or add your own methods for specific requirements
  // ...
}
```
