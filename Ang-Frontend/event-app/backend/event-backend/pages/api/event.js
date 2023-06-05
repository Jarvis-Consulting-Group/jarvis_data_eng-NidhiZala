import fs from 'fs';
const dataPath = "/home/centos/dev/jarvis_data_eng_NidhiZala/angular-frontend/event-app/backend/event-backend/pages/api/data.json";

function loadData() {
  try {
    const data = fs.readFileSync(dataPath, 'utf-8');
    return JSON.parse(data);
  } catch (error) {
    return [];
  }
}

function saveData(events) {
  const data = JSON.stringify(events, null, 2);
  fs.writeFileSync(dataPath, data);
}

export default function handler(req, res) {
  res.header("Access-Control-Allow-Origin", "http://localhost:4200"); 
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
 
  let events = loadData(); // Load events from file
  const corsHandler = cors({
    origin: 'http://localhost:4200', // Replace with the appropriate frontend URL
    methods: ['GET', 'POST', 'PUT', 'DELETE'], // Add the required methods
    allowedHeaders: ['Content-Type'],
  });
  console.log (events);
  if (req.method === 'GET') {
    res.status(200).json(events);
  } else if (req.method === 'POST') {
    const { name, host, date, location } = req.body;
    const newEvent = {
      id: events.length += 1,
      name,
      host,
      date,
      location,
    };
    events.push(newEvent); // Add new event to the loaded events array
    saveData(events); // Save the updated events to the file
    res.status(201).json(newEvent);
  } else if (req.method === 'PUT') {
    res.status(405).json({ error: 'PUT Method Not Allowed' });
  } else if (req.method === 'DELETE') {
    res.status(405).json({ error: 'Delete Method Not Allowed' });
  } else {
    res.status(400).json({ error: 'Bad Request' });
  }
}
