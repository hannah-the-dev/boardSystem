package com.hannahj.springBoard.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Board {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	@Setter 
	private String title;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="catetgory_id")
	private Category category;
	
	@Singular
	@Setter
	@OneToMany(mappedBy="board", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Post> posts;
}

