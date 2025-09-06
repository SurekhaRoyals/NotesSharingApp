package com.nsa.base.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsa.base.Entity.Note;
import com.nsa.base.NoteDtos.NoteResponse;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{

	
	@Query(
			  value = """
			    SELECT 
			      n.id AS id,
			      n.title AS title,
			      n.content AS content,
			      n.tags AS tags,
			      n.created_at AS createdAt,
			      n.updated_at AS updatedAt,
			      u.username AS ownerUsername
			    FROM notes n
			    JOIN users u ON n.owner_id = u.id
			    WHERE n.owner_id = :ownerId
			    ORDER BY n.created_at DESC
			  """,
			  countQuery = """
			    SELECT COUNT(*) 
			    FROM notes n 
			    WHERE n.owner_id = :ownerId
			  """,
			  nativeQuery = true
			)
		  Page<NoteResponse> findMyNotes(@Param("ownerId") Long ownerId, Pageable pageable);

	@Query(
			  value = """
			    SELECT 
			      n.id AS id,
			      n.title AS title,
			      n.content AS content,
			      n.tags AS tags,
			      n.created_at AS createdAt,
			      n.updated_at AS updatedAt,
			      u.username AS ownerUsername
			    FROM notes n
			    JOIN users u ON n.owner_id = u.id
			    WHERE n.owner_id = :ownerId
			      AND (
			        LOWER(n.title)   LIKE LOWER(CONCAT('%', :q, '%'))
			        OR LOWER(n.content) LIKE LOWER(CONCAT('%', :q, '%'))
			        OR LOWER(n.tags)    LIKE LOWER(CONCAT('%', :q, '%'))
			      )
			    ORDER BY n.created_at DESC
			  """,
			  countQuery = """
			    SELECT COUNT(*) 
			    FROM notes n
			    WHERE n.owner_id = :ownerId
			      AND (
			        LOWER(n.title)   LIKE LOWER(CONCAT('%', :q, '%'))
			        OR LOWER(n.content) LIKE LOWER(CONCAT('%', :q, '%'))
			        OR LOWER(n.tags)    LIKE LOWER(CONCAT('%', :q, '%'))
			      )
			  """,
			  nativeQuery = true
			)
		  Page<NoteResponse> searchMyNotes(@Param("ownerId") Long ownerId, @Param("q") String q, Pageable pageable);
		
	
	
	
}
