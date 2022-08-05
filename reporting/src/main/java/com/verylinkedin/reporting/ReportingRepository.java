package com.verylinkedin.reporting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportingRepository extends JpaRepository<Report, Long> {
}
//public interface ReportingRepository  {
//}