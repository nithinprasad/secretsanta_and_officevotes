/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 982168
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResult {

	private User user;

	private Long vote;

}
