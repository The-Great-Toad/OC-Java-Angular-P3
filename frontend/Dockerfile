# Stage 1: Build the Angular application
FROM node:18-alpine as build

WORKDIR /app

COPY package*.json ./
COPY . .

RUN npm install
RUN npm run build

# Stage 2: Serve the application with Nginx
FROM nginx:alpine

# Copy the built application from the build stage
COPY --from=build /app/dist/estate /usr/share/nginx/html

# Copy custom nginx configuration
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80