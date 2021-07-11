package challenge.javarestful;

import org.springframework.data.jpa.repository.JpaRepository;

interface ImageRepository extends JpaRepository<Image, Long> {

}