PREGUNTA 1
Explica cómo funciona la relación 1:N entre CentroForense y MuestraForense tanto en SQL como en Java.
-> La relacion es 1:N ya que un centro forense puede tener muchas muestras registradas en su interior, pero una muestra solo puede pertenecer a un centro forense, 
esto nos permite acceder a los datos de ambas tablas usando un inner join using (ID_CENTRO_FORENSE), ya que al ser 1:N ambas tablas tendrán el D_CENTRO_FORENSE, 
un centro forense como primary key y una muestra como foreign key.

PREGUNTA 2
-> Explica por qué en Java utilizamos: private CentroForense centro; y no: private int centroId;
El pasarle directamente por parametro el objeto en vez de un atributo, nos permite acceder tambien directamente a todos los atributos de esa muestra, y no solo a su id,
lo que facilita las consultas mediante la posibilidad de usar inner joins. 

PREGUNTA 3
Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.
-> El PreparedStatement nos permite realizar consultas avanzadas sin tener que dar todos los detalles en los selects, permitiendo usar ? ? ? como puntos universales
dentro del selecten el que después serán definidos a la hora de realizar la consulta, sin tener que crear infinidad de consultas para cada caso, como por ejemplo en 
SELECT * FROM CENTRO_FORENSE WHERE id = ?, que posteriormente se podrá reutilizar en casos como:
motorSQL.getPs().setString(1, centroForense.getNombre());
motorSQL.getPs().setString(2, centroForense.getPais());
motorSQL.getPs().setString(3, centroForense.getNivelSeguridad());
