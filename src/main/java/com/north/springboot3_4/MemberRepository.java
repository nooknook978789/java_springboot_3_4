package com.north.springboot3_4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel,Long> {
    MemberModel findByMobile(String mobile);

    void deleteByMobile(String mobile);
}
