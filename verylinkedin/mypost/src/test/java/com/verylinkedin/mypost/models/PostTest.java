//package com.verylinkedin.mypost.models;
//
//import com.verylinkedin.mypost.PostRepository;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@Component
//@AllArgsConstructor
//@Slf4j
//class PostTest {
//
//    @Test
//    void createPostTest() {
//        PostRepository postRepository =new PostRepository() {
//            @Override
//            public Post findById(String id) {
//                return null;
//            }
//
//            @Override
//            public void deleteById(String id) {
//
//            }
//
//            @Override
//            public List<Post> findByUserId(String id) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> List<S> saveAll(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public List<Post> findAll() {
//                return null;
//            }
//
//            @Override
//            public List<Post> findAll(Sort sort) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> S insert(S entity) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> List<S> insert(Iterable<S> entities) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> List<S> findAll(Example<S> example) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> List<S> findAll(Example<S> example, Sort sort) {
//                return null;
//            }
//
//            @Override
//            public Page<Post> findAll(Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> S save(S entity) {
//                return null;
//            }
//
//            @Override
//            public Optional<Post> findById(Integer integer) {
//                return Optional.empty();
//            }
//
//            @Override
//            public boolean existsById(Integer integer) {
//                return false;
//            }
//
//            @Override
//            public Iterable<Post> findAllById(Iterable<Integer> integers) {
//                return null;
//            }
//
//            @Override
//            public long count() {
//                return 0;
//            }
//
//            @Override
//            public void deleteById(Integer integer) {
//
//            }
//
//            @Override
//            public void delete(Post entity) {
//
//            }
//
//            @Override
//            public void deleteAllById(Iterable<? extends Integer> integers) {
//
//            }
//
//            @Override
//            public void deleteAll(Iterable<? extends Post> entities) {
//
//            }
//
//            @Override
//            public void deleteAll() {
//
//            }
//
//            @Override
//            public <S extends Post> Optional<S> findOne(Example<S> example) {
//                return Optional.empty();
//            }
//
//            @Override
//            public <S extends Post> Page<S> findAll(Example<S> example, Pageable pageable) {
//                return null;
//            }
//
//            @Override
//            public <S extends Post> long count(Example<S> example) {
//                return 0;
//            }
//
//            @Override
//            public <S extends Post> boolean exists(Example<S> example) {
//                return false;
//            }
//        };
//        Post post = Post.builder()
//                .userId("5252")
//                .content("Test")
//                .build();
//        post.setPublic(true);
//        postRepository.save(post);
//System.out.println(postRepository+" "+post+"  "+postRepository.findById("123456789"));
//        assertEquals(postRepository.findById("123456789").isPublic(), true);
//    }
//
//    @Test
//    void getId() {
//    }
//
//    @Test
//    void getContent() {
//    }
//
//    @Test
//    void getIsPublic() {
//    }
//
//    @Test
//    void getMedia() {
//    }
//
//    @Test
//    void setUserId() {
//    }
//
//    @Test
//    void setId() {
//    }
//
//    @Test
//    void setContent() {
//    }
//
//    @Test
//    void setIsPublic() {
//    }
//
//    @Test
//    void setMedia() {
//    }
//
//    @Test
//    void builder() {
//    }
//}