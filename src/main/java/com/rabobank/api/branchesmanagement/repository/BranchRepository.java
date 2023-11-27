package com.rabobank.api.branchesmanagement.repository;

import com.rabobank.api.branchesmanagement.document.BranchDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends MongoRepository<BranchDocument, String> {
}