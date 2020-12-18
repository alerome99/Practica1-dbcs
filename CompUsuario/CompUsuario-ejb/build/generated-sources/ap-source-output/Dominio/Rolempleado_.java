package Dominio;

import Dominio.Empleado;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-11-05T19:20:02")
@StaticMetamodel(Rolempleado.class)
public class Rolempleado_ { 

    public static volatile SingularAttribute<Rolempleado, Short> idrol;
    public static volatile SingularAttribute<Rolempleado, String> nombrerol;
    public static volatile CollectionAttribute<Rolempleado, Empleado> empleadoCollection;

}