/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{html,ts}',
  ],
  theme: {
    extend: {
      colors: {
        primary: '#0A0A0A',
        secondary: '#1D1D1D',
        tertiary: '#517BFE',
        textprimary: '#FEFEFE',
        textsecondary: '#CFDBD5',
      }
    },
  },
  plugins: [],
}

