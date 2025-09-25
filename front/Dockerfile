# Stage 1: Build React app
FROM node:18-alpine AS build
WORKDIR /app

# Copy và cài dependencies
COPY package*.json ./
RUN npm install

# Copy source code và build
COPY . .
RUN npm run build

# Stage 2: Serve bằng Nginx
FROM nginx:alpine
# Xoá default nginx index
RUN rm -rf /usr/share/nginx/html/*
# Copy kết quả build từ stage 1
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
