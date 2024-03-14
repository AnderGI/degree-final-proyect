FROM node:latest

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /usr/src/app

# Instala las dependencias del proyecto
COPY package*.json ./
RUN npm install

# Construye la aplicación (este paso ya está hecho fuera del Dockerfile)
# COPY . .
# RUN npm run build

# Elimina los archivos que no son necesarios para la imagen final
RUN rm -rf node_modules

# Copia el archivo de salida de la compilación en la imagen desde la carpeta dist
COPY dist/app.bundle.cjs .

# Expone el puerto en el que se ejecuta tu aplicación
EXPOSE 3000

# Comando para ejecutar tu aplicación
CMD ["node", "app.bundle.cjs"]

