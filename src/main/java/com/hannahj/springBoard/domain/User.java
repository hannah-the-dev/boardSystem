package com.hannahj.springBoard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
public class User extends BaseTimeEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String email;

   @Column
   private String picture;
   
   @Singular
   @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   private List<Post> posts;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private Role role;

   @Builder
   public User(String name, String email, String picture, Role role) {
       this.name = name;
       this.email = email;
       this.picture = picture;
       this.role = role;
   }

   public User update(String name, String picture) {
       this.name = name;
       this.picture = picture;

       return this;
   }

   public String getRoleKey() {
       return this.role.getKey();
   }
}