package com.hannahj.springBoard.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;


@Getter @Setter 
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Board {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String title;
	
	@Setter
	@Singular
	@OneToMany(mappedBy="board", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<BoardItem> boardItems;
	
	
}

