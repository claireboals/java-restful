package challenge.javarestful;

class EntityNotFoundException extends RuntimeException {

  EntityNotFoundException(Long id) {
    super("Could not find entity with id: " + id);
  }
}