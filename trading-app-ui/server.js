const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const app = express();
const port = 3000;
const fs = require('fs');

app.use(bodyParser.json());
app.use(cors());

let traderList = [];

// Load trader data from JSON file into memory when the server starts
fs.readFile('./src/assets/data.json', 'utf8', (err, data) => {
  if (err) {
    console.error(err);
  } else {
    traderList = JSON.parse(data);
    console.log('Trader data loaded successfully.');
  }
});

// Endpoint for retrieving a specific trader
app.get('/api/traders/:id', (req, res) => {
    const id = req.params.id;
    console.log('GET Trader ID:', id);
  
    // Read the trader data from the JSON file
    const traderList = require('./src/assets/data.json');
  
    // Find the trader with the specified ID
    const trader = traderList.find(trader => trader.id === parseInt(id));
  
    if (trader) {
      res.json(trader);
    } else {
      res.status(404).json({ error: 'Trader not found' });
    }
  });
  


// Endpoint for deleting a trader
app.delete('/api/traders/:id', (req, res) => {
  const id = req.params.id;
  // Read the trader data from the JSON file
  let traderList = require('./src/assets/data.json');
  // Find the index of the trader with the specified ID
  const index = traderList.findIndex(trader => trader.id === parseInt(id));
  if (index !== -1) {
    // Remove the trader from the traderList
    traderList.splice(index, 1);
    // Update the JSON file with the modified traderList
    fs.writeFile('./src/assets/data.json', JSON.stringify(traderList), err => {
      if (err) {
        console.error(err);
        res.status(500).json({ error: 'Failed to delete trader' });
      } else {
        res.json(traderList);
      }
    });
  } else {
    res.status(404).json({ error: 'Trader not found' });
  }
});

// Endpoint for adding a new trader
app.post('/api/traders', (req, res) => {
  const trader = req.body;
  // Read the trader data from the JSON file
  let traderList = require('./src/assets/data.json');
  // Generate a unique ID for the new trader
  const newId = generateUniqueId(traderList);
  // Assign the new ID to the trader object
  trader.id = newId;
  // Add the new trader to the traderList
  traderList.push(trader);
  // Update the JSON file with the modified traderList
  fs.writeFile('./src/assets/data.json', JSON.stringify(traderList), err => {
    if (err) {
      console.error(err);
      res.status(500).json({ error: 'Failed to add trader' });
    } else {
      res.json(traderList);
    }
  });
});

app.put('/api/traders/:id', (req, res) => {
    const id = req.params.id;
    const updatedTrader = req.body;
    // Read the trader data from the JSON file
    let traderList = require('./src/assets/data.json');
    // Find the index of the trader with the specified ID
    const index = traderList.findIndex(trader => trader.id === parseInt(id));
    if (index !== -1) {
      // Update the trader's information
      traderList[index] = { ...traderList[index], ...updatedTrader };
      // Update the JSON file with the modified traderList
      fs.writeFile('./src/assets/data.json', JSON.stringify(traderList), err => {
        if (err) {
          console.error(err);
          res.status(500).json({ error: 'Failed to update trader' });
        } else {
          res.json(traderList[index]);
        }
      });
    } else {
      res.status(404).json({ error: 'Trader not found' });
    }
  });
  
  app.listen(port, () => {
    console.log(`Server is running on port ${port}`);
  });

// Function to generate a unique ID for a new trader
function generateUniqueId(traderList) {
  const existingIds = traderList.map(trader => trader.id);
  let newId = Math.floor(Math.random() * 1000) + 1;

  // Generate a new ID until it becomes unique
  while (existingIds.includes(newId)) {
    newId = Math.floor(Math.random() * 1000) + 1;
  }

  return newId;
}
