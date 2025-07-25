# Etapa de construcción
FROM node:18-alpine AS build

WORKDIR /app

# Copiar archivos de dependencias
COPY package*.json ./

# Instalar dependencias
RUN npm ci

# Copiar el código fuente
COPY . .

# Compilar la aplicación para producción
RUN npm run build -- --configuration production

# Etapa de producción
FROM nginx:alpine

# Copiar los archivos compilados desde la etapa de construcción
COPY --from=build /app/dist/*/. /usr/share/nginx/html/

# Configuración personalizada de nginx para usar puerto 4200
RUN echo 'server { \
    listen 4200; \
    server_name localhost; \
    root /usr/share/nginx/html; \
    index index.html index.htm; \
    location / { \
        try_files $uri $uri/ /index.html; \
    } \
}' > /etc/nginx/conf.d/default.conf

# Exponer el puerto 4200
EXPOSE 4200

# Comando para iniciar nginx
CMD ["nginx", "-g", "daemon off;"]
