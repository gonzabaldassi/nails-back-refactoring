package jsges.nails.domain.services;

import jakarta.persistence.*;
import jsges.nails.domain.ObjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "ServiceType")
public class ServiceType extends ObjectType implements Serializable {

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceItem> serviceItems = new ArrayList<>();

}
