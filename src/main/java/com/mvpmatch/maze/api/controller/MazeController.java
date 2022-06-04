package com.mvpmatch.maze.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mvpmatch.maze.api.config.JwtTokenUtil;
import com.mvpmatch.maze.api.dto.MazeDTO;
import com.mvpmatch.maze.api.model.Maze;
import com.mvpmatch.maze.api.model.MazeWall;
import com.mvpmatch.maze.api.repository.MazeRepository;
import com.mvpmatch.maze.api.repository.MazeWallRepository;
import com.mvpmatch.maze.api.repository.UserRepository;

import io.jsonwebtoken.lang.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MazeController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MazeRepository mazeRepository;

	@Autowired
	private MazeWallRepository wallRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/maze")
	public ResponseEntity<Map<String, Object>> saveMaze(@RequestHeader("Authorization") String authorization, @RequestBody MazeDTO mazeDetail) throws Exception {
		ResponseEntity<Map<String, Object>> response;
		Authentication authObject = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		List<MazeWall> insertedRecord = new ArrayList<MazeWall>();
		try {
			// UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			// authenticationManager.authenticate(token);
			// SecurityContextHolder.getContext().setAuthentication(authObject);
			String token = authorization.replace("Bearer ", "");
			Map<String, Object> claims = new HashMap<>();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			System.out.println("username: " + username);
			System.out.println("data: " + mazeDetail.getWalls());
			if (userRepository.existsByEmail(username)) {
				Maze maze = new Maze();
				maze.entrance = mazeDetail.getEntrance();
				System.out.println("gs: "+mazeDetail.getGridsize());
				maze.gridsize = mazeDetail.getGridsize();
				Maze id = mazeRepository.save(maze);
				// Maze id = mazeRepository.findByEntrance(maze.entrance).get();
				List<MazeWall> mazeWall = new ArrayList<MazeWall>();
				List<String> wallDetail = mazeDetail.getWalls();	 
				for(int i=0;i<wallDetail.size();i++){
					MazeWall mazeW = new MazeWall();
					mazeW.wall = wallDetail.get(i);
					mazeW.mazeId = id.getId();
					mazeWall.add(mazeW);
					// insertedRecord.add(wallRepository.save(mazeW));
				}
				// wallRepository.saveAll(mazeWall);
				insertedRecord = wallRepository.saveAll(mazeWall);
				// insertedRecord = mazeRepository.findByGridsize(maze.gridsize).get();
				data.put("maze", id);
				data.put("mazewall", insertedRecord);
			} else {
				throw new Error("Username not found!!!");
			}
			// data.put("token", tokenData);
			map.put("data", data);
			map.put("code", HttpStatus.OK);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (BadCredentialsException e) {
			data.put("error", e.getMessage());
			map.put("data", data);
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return response;
	}
	
	

	@GetMapping("/maze/{mazeId}/solution")
	public ResponseEntity<Map<String, Object>> findMazeSolution(@RequestHeader("Authorization") String authorization,
	@PathVariable Long mazeId ,@RequestParam String steps) throws Exception {
		ResponseEntity<Map<String, Object>> response;
		Authentication authObject = null;
		System.out.println("mazeId: "+mazeId);
		System.out.println("steps: "+steps);
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			String token = authorization.replace("Bearer ", "");
			Map<String, Object> claims = new HashMap<>();
			String username = jwtTokenUtil.getUsernameFromToken(token);
			Maze mazeDetail = mazeRepository.findById(mazeId).get();
			List<MazeWall> mazeWallDetail = wallRepository.findAllByMazeId(mazeId).get();
			System.out.println("Maze: "+mazeDetail.getEntrance()+" => "+mazeDetail.getGridsize()+" Wall: "+mazeWallDetail);
			int gridSize = Integer.parseInt(mazeDetail.getGridsize().subSequence(0, 1).toString());
			char ch = 'A';
			String col = "";
			Map<String, Integer> mapsa = new HashMap<>();
			for(int i=0;i<gridSize;i++) {
				for(int j=0;j<gridSize;j++) {
					col = ch+""+(j+1);
					mapsa.put(col, 0);
					System.out.print(ch+""+(j+1)+"-"+" ");
				}
				ch++;
				col = "";
				System.out.println();
			}
			mazeWallDetail.forEach(item->{
				if(mapsa.containsKey(item.getWall())) {
					mapsa.put(item.getWall(), 1);
				}
			});
			for(Map.Entry<String, Integer> hm: mapsa.entrySet()) {
				System.out.println(hm.getKey()+" => "+hm.getValue());
			}
			data.put("maze", mazeDetail);
			data.put("mazewall", mazeWallDetail);
			map.put("data", data);
			map.put("code", HttpStatus.OK);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);	
		} catch (BadCredentialsException e) {
			data.put("error", e.getMessage());
			map.put("data", data);
			map.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
			response = new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return response;
	}



	private boolean extracted(String col, MazeWall a) {
		return a.getWall().equals(col);
	}

}
