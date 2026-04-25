//SAFIIA MUSAEVA 751008598

package group1.tutoringcenter.repositories;

import group1.tutoringcenter.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {


    List<Admin> findByNameContainingIgnoreCase(String name);
    List<Admin> findByDepartmentIgnoreCase(String department);
    @Query("SELECT a FROM Admin a WHERE a.email = :email")
    Optional<Admin> findByEmailCustomQuery(@Param("email") String email);
    @Modifying
    @Transactional
    @Query("UPDATE Admin a SET a.department = :department WHERE a.id = :id")
    void updateDepartmentById(@Param("id") Long id, @Param("department") String department);
}