package com.saveuser.repository;

import com.saveuser.entity.OtpDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends CrudRepository<OtpDetails, Long> {

}
