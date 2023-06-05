import fs from 'fs';
import path from 'path';
import cors from 'cors';

const dataFilePath = '/home/centos/dev/jarvis_data_eng_NidhiZala/angular-frontend/crud-app-final/backend/crud-app-backend/pages/api/data.json';

// Load data from JSON file
function loadData() {
  try {
    const data = fs.readFileSync(dataFilePath, 'utf-8');
    return JSON.parse(data);
  } catch (error) {
    console.error('Error loading data:', error);
    return [];
  }
}

// Save data to JSON file
function saveData(items) {
  try {
    const data = JSON.stringify(items, null, 2);
    fs.writeFileSync(dataFilePath, data);
  } catch (error) {
    console.error('Error saving data:', error);
  }
}

const items = loadData();

// Enable CORS
const corsHandler = cors({
  origin: 'http://localhost:4200', // Replace with the appropriate frontend URL
  methods: ['GET', 'POST', 'PUT', 'DELETE'], // Add the required methods
  allowedHeaders: ['Content-Type'],
});

export default function handler(req, res) {
  
  // Use the corsHandler middleware
  corsHandler(req, res, () => {
    console.log('Request Method:', req.method);
    console.log('Request Body:', req.body);

    if (req.method === 'GET') {
      const { id } = req.query;
      if (id) {
        // READ - GET by ID
        const item = items.find((item) => item.id === parseInt(id));
        if (item) {
          res.status(200).json(item);
        } else {
          res.status(404).json({ error: 'Item not found' });
        }
      } else {
        // READ - GET all items
        res.status(200).json(items);
      }
    } else if (req.method === 'POST') {
      // CREATE
      const { name, description } = req.body;
      const newItem = {
        id: items.length + 1,
        name,
        description,
      };
      items.push(newItem);
      saveData(items); // Save updated data to JSON file
      res.status(201).json(newItem);
    } else if (req.method === 'PUT') {
      // UPDATE
      const { id, name, description } = req.body;
      const itemIndex = items.findIndex((item) => item.id === id);
      if (itemIndex !== -1) {
        const updateItem = {
          id,
          name,
          description,
        };
        items[itemIndex] = updateItem;
        saveData(items); // Save updated data to JSON file
        res.status(200).json(updateItem);
      } else {
        res.status(404).json({ error: 'Item not found' });
      }
    } else if (req.method === 'DELETE') {
      // DELETE
      const { id } = req.query;
      const itemIndex = items.findIndex((item) => item.id === parseInt(id));
      if (itemIndex !== -1) {
        items.splice(itemIndex, 1);
        saveData(items); // Save updated data to JSON file
        res.status(200).json({ message: 'Item deleted successfully' });
      } else {
        res.status(404).json({ error: 'Item not found' });
      }
    } else {
      res.status(405).json({ error: 'Wrong operation. Method not allowed' });
    }
  });
}
