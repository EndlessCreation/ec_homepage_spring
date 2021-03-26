package com.endlesscreation.ecsite.repository;

import com.endlesscreation.ecsite.domain.Position;
import com.endlesscreation.ecsite.domain.hashtag.MemberHashtag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.EnumSet;
import java.util.List;

public interface MemberHashtagRepository extends JpaRepository<MemberHashtag, Long>
{
    @Query(value = "SELECT distinct mh from MemberHashtag mh join fetch mh.hashtag h join fetch mh.member m where m.position IN :position ORDER BY mh.orderBy")
    List<MemberHashtag> findAllByMember_Position(@Param("position") List<Position> position);

    @Query(value = "SELECT distinct mh from MemberHashtag mh join fetch mh.hashtag h join fetch mh.member m where m.position = :position ORDER BY m.position", countQuery = "select count (mh) from MemberHashtag mh join mh.hashtag h join mh.member m where m.position = :position ")
    Page<MemberHashtag> findAllByMember_Position(Position position, Pageable pageable);
}
