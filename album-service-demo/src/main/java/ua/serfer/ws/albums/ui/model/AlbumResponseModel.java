/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.serfer.ws.albums.ui.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumResponseModel {
    private String albumId;
    private String userId; 
    private String name;
    private String description;

}
