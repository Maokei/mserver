package se.maokei.mserver.api.v1.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Comment;
import se.maokei.mserver.repository.CommentRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentController {
  private CommentRepository commentRepository;

  @PostMapping("/comment")
  private Mono<Comment> createComment(@Valid @RequestBody Comment comment) {
    return commentRepository.save(comment);
  }

  @PutMapping("/comment")
  private Mono<ResponseEntity<Comment>> updateComment(@Valid @RequestBody Comment.CommentDto dto) {
    Mono<Comment> found = commentRepository.findById(dto.id());

    return Mono.zip((data) -> {
      Comment c1 = (Comment) data[0];
      Comment c2 = (Comment) data[1];

      if (c2.getComment() != null && c2.getComment().length() > 2) {
        c1.setComment(c2.getComment());
      }
      return c1;
      }, found, Mono.just(dto.toComment())
    ).flatMap(this.commentRepository::save)
    .flatMap(res -> Mono.just(ResponseEntity.ok().body(res)))
    .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()));
  }

  @GetMapping("/comment")
  private Flux<Comment> getAllComments() {
    return commentRepository.findAll();
  }
}