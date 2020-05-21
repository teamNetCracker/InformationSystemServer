import * as express from 'express';
import { NextFunction, Request, Response } from 'express-serve-static-core';
import { routerFactory } from './routerFactory';

const app = express();
const port = process.env.NODEJS_PORT || 3000;
const root = '/';

const allowCrossDomain = (req: Request, res: Response, next: NextFunction): void => {
  res.header('Access-Control-Allow-Origin', '*');
  res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
  res.header('Access-Control-Allow-Headers', 'Content-Type');
  next();
};

const routers = [
  {
    url: 'server',
    middleware: routerFactory(),
  }
];

app.use(allowCrossDomain);

routers.forEach(router => app.use(root + router.url, router.middleware));

app.listen(port, () => console.log(`Mock server is listening on port ${port}`));
