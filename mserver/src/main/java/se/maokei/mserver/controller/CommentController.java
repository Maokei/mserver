package se.maokei.mserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Comment;
import se.maokei.mserver.repository.CommentRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
  private CommentRepository commentRepository;

  @PostMapping
  private Mono<Comment> createComment(@RequestBody Comment comment) {
    return commentRepository.save(comment);
  }

  @GetMapping
  private Flux<Comment> getAllComments() {
    return commentRepository.findAll();
  }
}
