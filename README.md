# ALURA
# Challenge: Conversor de Monedas
Este proyecto es un conversor de monedas desarrollado en Java, que consume datos en tiempo real desde la [ExchangeRate API](https://www.exchangerate-api.com/). La aplicación corre desde la consola y permite convertir entre distintas monedas del mundo con solo unos pocos comandos.

## Funcionalidades Principales

1. **Conversión USD a monedas latinoamericanas:**
  - Dólar → Peso Argentino (ARS)
  - Dólar → Peso Chileno (CLP)
  - Dólar → Peso Colombiano (COP)

2. **Conversión inversa:**
  - Peso Argentino (ARS) → Dólar

3. **Validaciones:**
  - Verificación de entrada numérica
  - Validación de montos positivos
  - Manejo de errores de conexión
  - Verificación de API Key

## Instalación y Configuración
1. Clona el repositorio:
```bash
    git clone https://github.com/CloRevollo/alura_conversionMoneda.git
```
2. Agrega tu clave de API (obtenla desde ExchangeRate API) y reemplaza en el archivo .env:
```bash
    cp .envExample .env
    # Edita el archivo .env y agrega tu API Key
```

### Challenge Alura - Oracle Next Education G8
Este proyecto fue desarrollado como parte del programa de **Oracle Next Education** en colaboración con **Alura LATAM**, enfocado en el desarrollo de habilidades prácticas en programación Java (Back-End)