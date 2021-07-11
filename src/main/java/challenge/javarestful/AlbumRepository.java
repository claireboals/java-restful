package challenge.javarestful;

import org.springframework.data.jpa.repository.JpaRepository;

interface AlbumRepository extends JpaRepository<Album, Long> {

}