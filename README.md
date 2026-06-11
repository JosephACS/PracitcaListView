Java
#  Desarrollo de Aplicación Móvil: Consumo de API REST UTEQ con ListView y Seguridad SSL/TLS

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![Volley](https://img.shields.io/badge/Library-Volley-blue?style=for-the-badge)
![Coil](https://img.shields.io/badge/Library-Coil-teal?style=for-the-badge)

---

##  Información Académica
*   **Institución:** Universidad Técnica Estatal de Quevedo (UTEQ)
*   **Facultad:** Facultad de Ciencias de la Ingeniería
*   **Carrera:** Ingeniería en Software / Computación
*   **Asignatura:** Aplicaciones Móviles
*   **Nivel:** 6to Nivel "A/B"
*   **Corte:** 1
*   **Tema:** Consumo de Servicios Web RESTful con autenticación Bearer y visualización en ListView dinámico.

---

##  1. Descripción General
Este proyecto consiste en el desarrollo de una aplicación móvil nativa en Android utilizando **Kotlin**. El objetivo principal es consumir un endpoint específico de la API institucional de la **UTEQ** para recuperar y mostrar los 10 vídeos más recientes de resúmenes semanales.

La aplicación implementa una arquitectura robusta que maneja la persistencia en memoria mediante el patrón Singleton para las peticiones de red, seguridad personalizada mediante certificados SSL/TLS para la conexión con el servidor institucional, y un diseño moderno basado en **Material Design 3**.

---

##  2. Objetivos

### Objetivo General
Desarrollar una aplicación móvil en Android que integre el consumo de APIs RESTful seguras para la gestión y visualización de contenido multimedia institucional.

### Objetivos Específicos
1.  Implementar el consumo de servicios web mediante la librería **Volley** con manejo de headers de autenticación.
2.  Configurar un **HurlStack** personalizado y un **X509TrustManager** para permitir conexiones seguras con servidores institucionales.
3.  Utilizar un **BaseAdapter** personalizado para la renderización eficiente de datos en un **ListView**.
4.  Gestionar la carga asíncrona de imágenes mediante la librería **Coil**, optimizando el uso de memoria y caché.

---

##  3. Tecnologías Utilizadas

| Tecnología | Propósito |
| :--- | :--- |
| **Kotlin** | Lenguaje de programación principal (Modern Android Dev). |
| **Volley** | Gestión de peticiones HTTP asíncronas y colas de red. |
| **Coil** | Carga de imágenes moderna basada en Corrutinas de Kotlin. |
| **Material 3** | Implementación de componentes de diseño visual avanzado. |
| **JSON** | Formato de intercambio de datos entre API y App. |
| **SSL/TLS Custom** | Manejo de seguridad de red para certificados específicos. |

---

##  4. Arquitectura del Proyecto
Se ha implementado una arquitectura organizada por responsabilidades para facilitar el mantenimiento y la escalabilidad del código:

*   **Modelo (Data Class):** Representación de la entidad `Video`.
*   **Vista (XML & Activity):** Definición de la interfaz de usuario y renderizado.
*   **Controlador/Adaptador:** Lógica intermedia para vincular los datos JSON con los componentes visuales del ListView.
*   **Servicio (Networking):** Capa encargada de la comunicación externa y configuración de seguridad.

---

##  5. Estructura de Carpetas
Show full code block
 6. Consumo de API y Autenticación
Detalle del Endpoint
•
URL: https://apiws.uteq.edu.ec/h6RPoSoRaah0Y4Bah28eew/functions/information/entity/3
•
Método: GET
•
Autenticación: Bearer Token (Enviado en el Header Authorization).
Construcción de Imágenes
La API devuelve una ruta parcial en el campo portadaVideo. El sistema concatena dinámicamente la URL base: https://uteq.edu.ec/assets/images/videos/res-sem/ + portada_devuelta.jpg
 7. Seguridad y Manejo de SSL/TLS
Debido a requerimientos específicos de los servidores institucionales, se implementó un manejo avanzado de seguridad:
•
HurlStack Personalizado: Para interceptar y gestionar el handshake SSL.
•
Network Security Configuration: Para declarar explícitamente la confianza en los dominios de la UTEQ y prevenir ataques Man-in-the-Middle.
•
X509TrustManager: Implementación de un gestor de confianza para validar la cadena de certificados del servicio web.
 8. Dependencias Principales (build.gradle)
Kotlin
---

##  6. Consumo de API y Autenticación

### Detalle del Endpoint
*   **URL:** `https://apiws.uteq.edu.ec/h6RPoSoRaah0Y4Bah28eew/functions/information/entity/3`
*   **Método:** `GET`
*   **Autenticación:** `Bearer Token` (Enviado en el Header `Authorization`).

### Construcción de Imágenes
La API devuelve una ruta parcial en el campo `portadaVideo`. El sistema concatena dinámicamente la URL base:
`https://uteq.edu.ec/assets/images/videos/res-sem/` + `portada_devuelta.jpg`

---

##  7. Seguridad y Manejo de SSL/TLS
Debido a requerimientos específicos de los servidores institucionales, se implementó un manejo avanzado de seguridad:
*   **HurlStack Personalizado:** Para interceptar y gestionar el handshake SSL.
*   **Network Security Configuration:** Para declarar explícitamente la confianza en los dominios de la UTEQ y prevenir ataques Man-in-the-Middle.
*   **X509TrustManager:** Implementación de un gestor de confianza para validar la cadena de certificados del servicio web.

---

##  8. Dependencias Principales (build.gradle)
Show full code block
 9. Vista Previa
Listado de Videos
Detalle del Item
Main Screen
Item Detail
Visualización general en ListView
Uso de Material CardView y Coil
 10. Instalación y Ejecución
1.
Clonar el repositorio:
Java
---

##  9. Vista Previa

| Listado de Videos | Detalle del Item |
| :---: | :---: |
| ![Main Screen](https://via.placeholder.com/200x400?text=Lista+de+Videos) | ![Item Detail](https://via.placeholder.com/200x150?text=Card+Video) |
| *Visualización general en ListView* | *Uso de Material CardView y Coil* |

---

##  10. Instalación y Ejecución
1.  Clonar el repositorio:
2.
Abrir el proyecto en Android Studio (Ladybug o superior).
3.
Sincronizar el proyecto con los archivos de Gradle.
4.
Configurar el Bearer Token en la clase MainActivity.kt.
5.
Ejecutar en un dispositivo físico o emulador con API 24 o superior.
  11. Conclusiones
•
La implementación del ListView con un BaseAdapter personalizado permitió una gestión precisa de la memoria al reciclar las vistas de los elementos no visibles.
•
La integración de Volley con una configuración SSL personalizada garantizó que el consumo de datos fuera exitoso incluso bajo entornos de certificados restringidos.
•
El uso de Coil mejoró significativamente la fluidez de la aplicación (UX), eliminando bloqueos en el hilo principal durante la carga de portadas de video.
 12. Referencias
•
Android Developers - Networking with Volley
•
Coil-kt Documentation
•
Material Design 3 Guidelines
•
UTEQ API Documentation
Developed with ❤️ by [Joseph Calderon] - UTEQ 2024
