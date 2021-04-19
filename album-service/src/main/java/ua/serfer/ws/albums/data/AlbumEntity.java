/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.serfer.ws.albums.data;

import com.sun.corba.se.spi.ior.Identifiable;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "albums")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "album_id")
    private String albumId;

    @Column(name = "user_id")
    private String userId; 

    private String name;

    private String description;

}
