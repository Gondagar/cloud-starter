package cc.serfer.ws.user.model;


import cc.serfer.ws.user.transfer.AlbumResponseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable =  false, unique = true)
    private String userId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable =  false, length = 50, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable =  false)
    private String encryptedPassword;

    @Transient
    List<AlbumResponseModel> albums;

    @Column(name = "is_active")
    private boolean isActive;

}
