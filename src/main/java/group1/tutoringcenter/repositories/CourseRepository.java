// Husna Syeda
// UID: 393005210
// Repository: CourseRepository
// ISTE 240 - Group 1

package group1.tutoringcenter.repositories;

import group1.tutoringcenter.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{


    @Query("SELECT c FROM Course c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Course> findByCourseName(@Param("name") String name);

    List<Course> findByDepartment(String department);

    Optional<Course> findByCourseCode(String courseCode);

    List<Course> findByTutorId(int tutorId);


    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.courseName = ?2 WHERE c.courseId = ?1")
    void updateCourseNameById(int id, String courseName);

    @Modifying
    @Transactional
    @Query("UPDATE Course c SET c.tutor.id = ?2 WHERE c.courseId = ?1")
    void updateTutorById(int id, int tutorId);

}
