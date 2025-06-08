# DAM135 - Guía de Aplicación UsoOnClick y diálogos

## Integrantes
- Alas Hernández, Milton Obed - AH09062
- Martínez Valladares, David Salomón MV12013

## **Descripción**

Aplicación Android desarrollada como parte de la Guía Práctica 5.5 - Diálogos Emergentes para la materia de Desarrollo de Aplicaciones Móviles. Esta aplicación demuestra el uso de diferentes tipos de diálogos emergentes en Android, incluyendo AlertDialog, DatePickerDialog, TimePickerDialog y diálogos personalizados.

## **Funcionalidades Principales**

*   🌙 Cambio de Modo Oscuro/Claro
    *   Switch para alternar entre modo oscuro y claro
    *   Muestra un AlertDialog de notificación al cambiar el modo
    *   Actualiza el tema de la aplicación dinámicamente
    *   Indica el estado actual del modo en un campo de texto
*   ✅ Control de Habilitación de Funciones
    *   CheckBox para deshabilitar/habilitar el cambio de modo
    *   Implementa un AlertDialog de confirmación con opciones "Sí" y "No"
    *   Maneja la lógica de habilitación del Switch según la selección del usuario
*   🖼️ Gestión de Visibilidad de Imagen
    *   RadioGroup con opciones para mostrar/ocultar imagen
    *   Al ocultar la imagen, muestra un diálogo personalizado que solicita justificación
    *   Permite ingresar una razón para ocultar la imagen
    *   Cambia la visibilidad del ImageView según la selección
*   📋 Lista Interactiva de Departamentos
    *   ListView con departamentos de El Salvador predefinidos
    *   AlertDialog con entrada de texto al hacer clic en un elemento
    *   Permite editar el texto de los elementos de la lista
    *   Actualiza dinámicamente el contenido de la lista
*   🖼️ Selección de Imágenes
    *   ImageView clickeable para seleccionar imágenes de la galería
    *   Integración con la galería del dispositivo
    *   Preserva la imagen seleccionada al cambiar entre modos
    *   Manejo de URI de imágenes
*   📅 Selector de Fecha
    *   ImageButton cona ícono de calendario
    *   DatePickerDialog para seleccionar fechas
    *   Muestra la fecha seleccionada en formato DD/MM/YYYY
    *   Inicializa con la fecha actual
*   ⏰ Selector de Hora
    *   ImageButton con ícono de reloj
    *   TimePickerDialog para seleccionar la hora
    *   Formato de 24 horas
    *   Muestra la hora seleccionada en formato HH:MM
*   🚪 Botón de Salida
    *   FloatingActionButton para cerrar la aplicación
    *   Funcionalidad de cierre limpio de la actividad

## **Tipos de Diálogos Implementados**

1.  AlertDialog de Notificación

    ```java
    new AlertDialog.Builder(MainActivity.this)
        .setTitle("Cambiando modo")
        .setMessage("Se cambiará el modo de la aplicación")
        .setPositiveButton("OK", null)
        .show();
    ```

2.  AlertDialog de Confirmación

    ```java
    new AlertDialog.Builder(MainActivity.this)
        .setTitle("Confirmar cambio de modo")
        .setMessage("Esta seguro que desea deshabilitar el cambio de modo")
        .setPositiveButton("Si", listener)
        .setNegativeButton("NO", listener)
        .show();
    ```

3.  AlertDialog con Entrada de Texto

    ```java
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    final EditText nuevotexto = new EditText(MainActivity.this);
    builder.setView(nuevotexto);
    builder.setPositiveButton("Actualizar", listener);
    ```

4.  DatePickerDialog

    ```java
    DatePickerDialog datePickerDialog = new DatePickerDialog(
        MainActivity.this,
        onDateSetListener,
        año, mes-1, día
    );
    ```

5.  TimePickerDialog

    ```java
    TimePickerDialog timePickerDialog = new TimePickerDialog(
        MainActivity.this,
        onTimeSetListener,
        hora-1, minutos, true
    );
    ```

6.  Diálogo Personalizado

    ```java
    LayoutInflater inflater = getLayoutInflater();
    View dialogView = inflater.inflate(R.layout.dialogo_personalizado, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setView(dialogView);
    ```

## **Estructura del Proyecto**

```yaml
app/
├── src/main/
│ ├── java/sv/edu/ues/dam135/usoonclick/
│ │ └── MainActivity.java
│ └── res/
│ └── layout/
│ ├── activity_main.xml
│ └── dialogo_personalizado.xml
```

## **Componentes de UI Utilizados**

*   Switch - Cambio de modo
*   CheckBox - Control de habilitación
*   RadioGroup/RadioButton - Selección de opciones
*   ListView - Lista de departamentos
*   ImageView - Visualización de imágenes
*   ImageButton - Botones de fecha y hora
*   FloatingActionButton - Botón de salida
*   EditText - Entrada de texto
*   TextView - Visualización de estado

## **Características Técnicas**

*   Persistencia de Estado
    *   Guarda y restaura la URI de la imagen seleccionada
    *   Mantiene el estado al cambiar entre modo oscuro/claro
*   Manejo de Intents
    *   Intent para acceder a la galería de imágenes
    *   Manejo de resultados de actividades
*   Gestión de Temas
    *   Implementación de modo oscuro/claro usando AppCompatDelegate
    *   Cambio dinámico de temas sin reiniciar la aplicación

## **Requisitos del Sistema**

*   Android API Level: Mínimo requerido según configuración del proyecto
*   Permisos: Acceso a almacenamiento para selección de imágenes

## **Objetivos Académicos Cumplidos**

*   ✅ Implementación de diálogos de notificación
*   ✅ Implementación de diálogos de confirmación
*   ✅ Implementación de diálogos con entrada de texto
*   ✅ Implementación de selectores de fecha y hora
*   ✅ Implementación de diálogos personalizados
*   ✅ Manejo de eventos onClick en diferentes componentes
*   ✅ Gestión de estado de la aplicación

## **Instalación y Uso**

*   Clonar el repositorio
*   Abrir el proyecto en Android Studio
*   Compilar y ejecutar en dispositivo o emulador
*   Interactuar con los diferentes componentes para probar las funcionalidades de diálogos

## **Desarrolladores**

*   Milton Alas - [@Milton-Alas](https://github.com/Milton-Alas)
*   David Salomón - [@DavidSalomonDev](https://github.com/DavidSalomonDev)

*Esta aplicación fue desarrollada como parte del curso de Desarrollo de Aplicaciones Móviles, siguiendo la Guía Práctica 5.5 sobre Diálogos Emergentes.*