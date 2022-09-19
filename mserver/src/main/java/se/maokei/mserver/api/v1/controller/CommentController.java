package se.maokei.mserver.api.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.maokei.mserver.model.Comment;
import se.maokei.mserver.repository.CommentRepository;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {
  private CommentRepository commentRepository;

  @PostMapping
  private Mono<Comment> createComment(@Valid @RequestBody Comment comment) {
    return commentRepository.save(comment);
  }

  @GetMapping
  private Flux<Comment> getAllComments() {
    return commentRepository.findAll();
  }
}