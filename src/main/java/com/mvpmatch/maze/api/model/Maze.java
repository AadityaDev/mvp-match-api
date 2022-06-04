package com.mvpmatch.maze.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.criteria.JoinType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.json.JSONArray;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity(name = "tbl_maze")
// @TypeDef(
//     name = "json",
//     typeClass = JoinType.class
// )
// @Data
// @AllArgsConstructor
// @NoArgsConstructor
@Entity(name = "tbl_maze")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Maze {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Column(name="entrance")
    public String entrance;
    @Column(name="gridsize")
    public String gridsize;

    // @Type(type = "list-array")
    // @Column(name="walls", columnDefinition = "json")
    // public List<String> walls = new ArrayList<String>();

    public Long getId() {
        return id;
    }

    public String getEntrance() {
        return entrance;
    }

    public String getGridsize() {
        return gridsize;
    }

    // public List<String> getWalls() {
    //     return walls;
    // }

}
