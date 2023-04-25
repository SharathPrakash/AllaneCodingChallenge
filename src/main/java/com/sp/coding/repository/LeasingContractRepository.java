package com.sp.coding.repository;

import com.sp.coding.entity.LeasingContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeasingContractRepository extends JpaRepository<LeasingContract,Long> {
}
