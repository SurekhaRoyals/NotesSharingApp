package com.nsa.base.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsa.base.Entity.NoteShare;
import com.nsa.base.Entity.SharePermission;
import com.nsa.base.NoteDtos.NoteResponse;

@Repository
public interface NoteShareRepository extends JpaRepository<NoteShare,Long>{

	
	Optional<NoteShare> findByNoteIdAndSharedWithId(Long noteId, Long userId);

	  @Modifying
	  @Query("delete from NoteShare s where s.note.id = :noteId and s.sharedWith.id = :userId")
	  void deleteShare(@Param("noteId") Long noteId, @Param("userId") Long userId);

	  @Query(
			  value = """
			    SELECT 
			      n.id            AS id,
			      n.title         AS title,
			      n.content       AS content,
			      n.tags          AS tags,
			      n.created_at    AS createdAt,
			      n.updated_at    AS updatedAt,
			      u.username      AS ownerUsername
			    FROM note_share s
			    JOIN notes n   ON s.note_id = n.id
			    JOIN users u   ON n.owner_id = u.id
			    WHERE s.shared_with_id = :userId
			    ORDER BY n.created_at DESC
			  """,
			  countQuery = """
			    SELECT COUNT(*)
			    FROM note_share s
			    JOIN notes n ON s.note_id = n.id
			    WHERE s.shared_with_id = :userId
			  """,
			  nativeQuery = true
			)
	  Page<NoteResponse> findSharedWithMe(@Param("userId") Long userId, Pageable pageable);

	  @Query(
			  value = """
			    SELECT s.permission
			    FROM note_share s
			    WHERE s.note_id = :noteId
			      AND s.shared_with_id = :userId
			    LIMIT 1
			  """,
			  nativeQuery = true
			)
	  Optional<SharePermission> permissionFor(@Param("noteId") Long noteId, @Param("userId") Long userId);
	
}
