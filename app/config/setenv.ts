const { writeFile } = require('fs');
const { argv } = require('yargs');
// read environment variables from .env file
require('dotenv').config();
// read the command line arguments passed with yargs
const environment = argv.environment;
const isDev = environment === 'dev';

if ( !process.env["WALMART_API_KEY"]) {
   console.error('All the required environment variables were not provided!');
   process.exit(-1);
}


const targetPath = isDev
   ? `./src/environments/environment.dev.ts`
   : `./src/environments/environment.ts`;
// we have access to our environment variables
// in the process.env object thanks to dotenv
const environmentFileContent = `
export const environment = {
   production: ${isDev},
   WALMART_API_KEY: "${process.env["WALMART_API_KEY"]}",
};
`;
// write the content to the respective file
writeFile(targetPath, environmentFileContent, function (err: any) {
   if (err) {
      console.log(err);
   }
   console.log(`Wrote variables to ${targetPath}`);
});


