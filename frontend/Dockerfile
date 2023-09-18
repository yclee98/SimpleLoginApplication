FROM node:18

WORKDIR /usr/app

COPY ./frontend/package.json .

RUN npm install

RUN npm install -g serve

COPY ./frontend/build ./build

EXPOSE 3000

# CMD ["npm", "start"]
CMD serve -s build