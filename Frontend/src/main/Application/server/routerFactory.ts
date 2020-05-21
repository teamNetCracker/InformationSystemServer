import * as express from 'express';
import { Router } from 'express';

export const routerFactory = (): Router => {
  const router = express.Router();

  router.get('/tracks', (req, res) => {
    res.status(200).json([
      {
        name: 'Name 1',
        author: 'Author 1'
      },
      {
        name: 'Name 2',
        author: 'Author 2'
      }
    ]);
  });

  router.get('/genres', (req, res) => {
    res.status(200).json([
      {
        name: 'Genre 1',
      },
      {
        name: 'Genre 2',
      }
    ]);
  });

  return router;
};
